package upc.fib.pes.grup121.service

import org.hibernate.annotations.common.util.impl.LoggerFactory
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import upc.fib.pes.grup121.model.Friendship
import upc.fib.pes.grup121.repository.FriendshipRepository
import java.util.Locale
import java.util.logging.Logger

@Service
class FriendshipService(
    private final var friendshipRepository: FriendshipRepository

) {
    fun getFriendshipsbyUsername(username: String, page:Int, size: Int): List<Friendship> {
        var friends: MutableList<Friendship>? = null;
        val sortByDate: PageRequest = PageRequest.of(page, size, Sort.by("friendId").ascending())
        try {
            friends = friendshipRepository.findAllByOwnerIdOrFriendId(username, username, sortByDate).toMutableList()
        }catch (e:Exception){
            throw Exception("Couldnt get friends")
        }
        return friends!!.toList();
    }
    fun insertFriendship(friendship: Friendship): Friendship? {
        try {
            friendshipRepository.findByOwnerIdAndFriendId(friendship.ownerId, friendship.friendId)
        }catch (e:Exception) {
            try{
                friendshipRepository.findByOwnerIdAndFriendId(friendship.friendId, friendship.ownerId);
            }catch (e:java.lang.Exception) {
                return friendshipRepository.save(friendship);
            }
        }
        return friendship;
    }

    fun getFriendshipbyId(id: Long): Friendship {
        val f = friendshipRepository.getReferenceById(id)
        return f
    }

}
