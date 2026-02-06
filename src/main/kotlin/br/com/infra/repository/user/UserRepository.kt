package br.com.infra.repository.user

import br.com.domain.entity.User
import com.mongodb.MongoException
import com.mongodb.client.model.Filters
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import org.bson.types.ObjectId
import org.slf4j.LoggerFactory

class UserRepository(
    mongoDatabase: MongoDatabase
): UserReadOnlyRepository, UserWriteOnlyRepository {

    private val logger = LoggerFactory.getLogger(UserRepository::class.java)
    private val usersCollection = mongoDatabase.getCollection<User>("users")

    override suspend fun findById(id: String): User? {
        try {
            return usersCollection.find(Filters.eq("_id", ObjectId(id))).firstOrNull()
        } catch (e: Exception) {
            logger.error("Error finding user by id: $id", e)
            return null
        }
    }

    override suspend fun findByIds(ids: List<String>): List<User>? {
        try {
            usersCollection.find(Filters.`in`("_id", ids.map { ObjectId(it) }))
        } catch (e: Exception) {
            logger.error("Error finding users by ids: $ids", e)
            when (e) {
                is MongoException -> logger.error("MongoException: ${e.message}")
                else -> logger.error("Error: ${e.message}")
            }
        }
        return emptyList()
    }

    override suspend fun checkIfExists(email: String): Boolean {
        try {
            val count = usersCollection.countDocuments(Filters.eq(User::email.name, email))
            return count > 0
        } catch (e: Exception) {
            logger.error("Error finding users by email: $email", e)
            when (e) {
                is MongoException -> logger.error("MongoException: ${e.message}")
                else -> logger.error("Error: ${e.message}")
            }
        }
        return false
    }

    override suspend fun checkIfExistsReturn(email: String): User? {
        try {
            return usersCollection.find(Filters.eq(User::email.name, email)).firstOrNull()
        } catch (e: Exception) {
            logger.error("Error finding users by email: $email", e)
            when (e) {
                is MongoException -> logger.error("MongoException: ${e.message}")
                else -> logger.error("Error: ${e.message}")
            }
        }
        return null
    }

    override suspend fun save(user: User): Boolean {
        try {
            return usersCollection.insertOne(user).wasAcknowledged()
        } catch (e: Exception) {
            logger.error("Error saving user: ${user.email}", e)
            when(e) {
                is MongoException -> logger.error("MongoException: ${e.message}")
                else -> logger.error("Error: ${e.message}")
            }
            return false
        }
    }
}