package ru.netology

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import kotlin.test.assertFailsWith


class WallServiceTest {

        @Before
        fun clearBeforeTest() {
            WallService.clear()
        }

        @Test
        fun updateExisting() {
            WallService.add(Post(1))
            WallService.add(Post(2))
            val count = 123
            val result =  WallService.update(Post(2, Likes(count)))
            Assert.assertEquals(true, result)
        }
        @Test
        fun updateNotExisting() {
            WallService.add(Post(1))
            WallService.add(Post(2))
            val count = 123
            val result =  WallService.update(Post(3, Likes(count)))
            Assert.assertEquals(false, result)

        }


        @Test
        fun addTest(){
            WallService.clear()
            WallService.add(Post(1))
            WallService.add(Post(2))
            Assert.assertEquals(true, WallService.posts.isNotEmpty())
        }

        @Test
        fun createCommentPositive(){
            val comment = Comment(
                id = 1,
                from_id = 123,
                date = 1624567890,
                text = "Test",
                reply_to_user = 456,
                reply_to_comment = 789
            )
            WallService.add(Post(1,Likes(0), AudioAttacment("audio", Audio(2,3,"Radiohead",556))))
            val result = WallService.createComment(1,comment)

            Assert.assertEquals(comment, result)

        }

        @Test
        fun createCommentException(){
            val comment = Comment(
                id = 1,
                from_id = 123,
                date = 1624567890,
                text = "Test",
                reply_to_user = 456,
                reply_to_comment = 789
            )
            WallService.add(Post(1,Likes(0), AudioAttacment("audio", Audio(2,3,"Radiohead",556))))

            assertFailsWith<WallService.PostNotFoundException> {
                WallService.createComment(11,comment)
            }

        }










    }






