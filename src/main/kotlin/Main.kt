package ru.netology


data class Post(
    var id: Int,
    var owner_id: Likes = Likes(0),
    var from_id: Int = 0,
    var created_by: Int = 0,
    var date: Int = 0,
    var text: String = "",
    var reply_owner_id: Int = 0,
    var reply_post_id: Int = 0,
    var friends_only: Boolean = false,
    var comments: Object = Object(),
    var copuright: String = "origins",
    var likes: Likes = Likes(0),
    var repost: Repost = Repost(),
    var views: Views = Views(),
    var post_type: String = "post",
    var post_sourse: PostSourse = PostSourse(),
    var copy_history: Array<String> = emptyArray(),
    var geo: Geo = Geo(),
    var singer_id: Int = 0,
    var can_pin: Boolean = true,
    var can_delete: Boolean = true,
    var can_edit: Boolean = true,
    var is_pinned: Boolean = false,
    var maker_as_ads: Boolean = false,
    var is_favorite: Boolean = false,
    var postoned_id: Int = 0
)

data class Geo(
    var type: String = "",
    var coordinates: String = "",
    var place: Place = Place(),

)

data class Place(
    var description: String = "none"
)


data class PostSourse(
    var count: Int = 0
)

data class Likes (
    var count: Int
)

data class Object(
    var count: Int = 0,
    var can_post: Boolean = true,
    var groups_can_post: Boolean = true,
    var can_close: Boolean = true,
    var can_open: Boolean = true
)

data class Repost(
    var count: Int = 0,
    var user_reposted: Boolean = false
)

data class Views(
    var count: Int = 0
)

interface Attachment{
    val type: String
}

data class Video(
    var id: Int,
    var owner_id: Int,
    var title: String,
    var duration: Int
)
data class VideoAttacment(override val type: String = "video", val video: Video ): Attachment

data class Audio(
    var id: Int,
    var owner_id: Int,
    var title: String,
    var duration: Int
)
data class AudioAttacment(override val type: String = "audio", val audio: Audio ): Attachment

data class Photo(
    var id: Int,
    var owner_id: Int,
    var photo_130: String,
    var photo_604: String
)
data class PhotoAttacment(override val type: String = "photo", val photo: Photo ): Attachment



object WallService{
    var posts = arrayOf<Post>()
    private var lastId = 0

    fun clear() {
        posts = emptyArray()
    }

    fun add(post: Post): Post {//add test

        posts += post.copy(id = ++lastId, likes = post.likes.copy())
        return posts.last()
    }

    fun print(){
        for (post in posts){
            print(post)
            print(' ')
        }
        println()
    }

    fun update(newPost: Post): Boolean{//add tests
        for ((index, post) in posts.withIndex()){
            if (post.id == newPost.id){
                posts[index] = newPost.copy(likes = newPost.likes.copy())
                return true
            }
        }
        return false


    }


}



fun main() {

    val post = Post(0)

    WallService.add(post)
    WallService.add(Post(1))
    WallService.add(Post(2))
    WallService.add(post)


    WallService.print()
    WallService.update(Post(2, Likes(123)))
    WallService.print()

    println(VideoAttacment("video", Video(1,2, "Granny", 30) ))
}