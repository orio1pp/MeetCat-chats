package upc.fib.pes.grup121.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import upc.fib.pes.grup121.model.Friendship

interface FriendshipRepository: JpaRepository<Friendship, Long> {

    fun findAllByOwnerId(ownerId:String): List<String>
}