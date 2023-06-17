package ru.netology


data class Post (
    var id: Int,
    var likes: Likes = Likes(0),
    var can_edit: Boolean = true,
    var post_type: String = "post",
    var text: String = "",
    var reply_post_id: Int = 0,
    var copy_history: Array<String> = emptyArray(),
    var copuright: String = "origins",
    var friends_only: Boolean = false,
    var is_favorite: Boolean = false



)

data class Likes (
    var count: Int
)


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

}