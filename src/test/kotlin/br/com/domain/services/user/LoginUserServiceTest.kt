package br.com.domain.services.user

import br.com.FakeUtil.Constants
import br.com.application.payloads.request.AuthUserRequestFactory
import br.com.application.payloads.response.TokenResponseFactory
import br.com.domain.model.UserFactory
import br.com.domain.services.password.BCryptPasswordService
import br.com.domain.services.token.TokenService
import br.com.domain.validations.AuthUserRequestValidation
import br.com.infra.repository.user.UserReadOnlyRepository
import br.com.utils.SuccessCodes
import com.google.common.truth.Truth
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

class LoginUserServiceTest {
//GIVEN-WHEN-THEN

    private lateinit var tokenService: TokenService
    private lateinit var bCryptPasswordService: BCryptPasswordService
    private lateinit var authUserRequestValidation: AuthUserRequestValidation
    private lateinit var userReadOnlyRepository: UserReadOnlyRepository

    private lateinit var loginUserService: LoginUserService

    private val userAnna = UserFactory().create(UserFactory.UserFake.Anna)

    private val tokenResponseFactory = TokenResponseFactory().create(
        successful = true,
        message = SuccessCodes.VALID_REGISTRATION.message,
        token = Constants.FAKE_TOKEN
    )

    private val authUserRequestFactory = AuthUserRequestFactory().create(
        email = userAnna.email,
        password = userAnna.password
    )

    @BeforeTest
    fun setUp() {
        tokenService = mockk()
        bCryptPasswordService = mockk()
        authUserRequestValidation = mockk()
        userReadOnlyRepository = mockk()

        loginUserService = LoginUserService(
            tokenService,
            bCryptPasswordService,
            authUserRequestValidation,
            userReadOnlyRepository
        )
    }

    @AfterTest
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `should return a successful tokenResponse with the generated token`() = runBlocking {
//        GIVEN
        val token = Constants.FAKE_TOKEN

        coEvery { authUserRequestValidation.validator(any()) } returns tokenResponseFactory
        coEvery { userReadOnlyRepository.checkIfExistsReturn(any()) } returns userAnna

        every { bCryptPasswordService.verify(any(), any()) } returns true
        every { tokenService.generateToken(any()) } returns token

        //WHEN
        val result = loginUserService.login(authUserRequestFactory)

//        THEN
        Truth.assertThat(result.token).isNotEmpty()
        Truth.assertThat(result.successful).isTrue()

    }

}