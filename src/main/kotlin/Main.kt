package ru.netology


data class Post(
    var id: Int,
    var owner_id: Likes = Likes(0),
    var attachment: Attachment? = null,
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

data class Comment(
    var id: Int,
    var from_id: Int,
    var date: Int,
    var text: String,
    var donut: Donut = Donut(),
    var reply_to_user: Int,
    var reply_to_comment: Int,
    var attachment: Attachment? = null,
    var parrent_stack: Array<Int> = emptyArray(),
    var thread: Thread = Thread()
)

data class Thread(
    var count: Int = 0,
    var items: Array<String> = emptyArray(),
    var can_post: Boolean = true,
    var chow_reply_button: Boolean = true,
    var grop_can_post: Boolean = true
)

data class Donut(
    var is_done: Boolean = true,
    var plaseholder: String = "none"
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
    private var comments = emptyArray<Comment>()
    private var lastId = 0
    class PostNotFoundException(message: String) : Exception(message)



    fun createComment(postId: Int, comment: Comment): Comment {

            val post = posts.find { it.id == postId }
            if (post != null) {
                comments += comment
                return comment
            } else {
                throw PostNotFoundException("Post with ID $postId not found")
            }

    }

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



fun main() {try{

    val post = Post(0, Likes(0))

    WallService.add(post)
    WallService.add(Post(1,Likes(0), AudioAttacment("audio", Audio(2,3,"Radiohead",556))))
    WallService.add(Post(2,Likes(4)))

    val comment = Comment(
        id = 1,
        from_id = 123,
        date = 1624567890,
        text = "Test",
        reply_to_user = 456,
        reply_to_comment = 789
    )
    println(WallService.createComment(1,comment))
    println()
    println()
    println()
    println(WallService.createComment(400,comment))
    println()
    println()
    println()




    WallService.print()
    WallService.update(Post(1, Likes(123),VideoAttacment("video", Video(1,2, "Granny", 30) )))
    WallService.print()

    //println(VideoAttacment("video", Video(1,2, "Granny", 30) ))
}catch (e: WallService.PostNotFoundException){println(e.message)}}