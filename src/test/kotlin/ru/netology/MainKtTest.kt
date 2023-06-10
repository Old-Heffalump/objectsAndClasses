package ru.netology

import org.junit.Assert
import org.junit.Before
import org.junit.Test


private const val count = 123


    class WallServiceTest {

        @Before
        fun clearBeforeTest() {
            WallService.clear()
        }

        @Test
        fun updateExisting() {
            WallService.add(Post(1))
            WallService.add(Post(2))
            val result =  WallService.update(Post(2, Likes(count)))
            Assert.assertEquals(true, result)
        }
        @Test
        fun updateNotExisting() {
            WallService.add(Post(1))
            WallService.add(Post(2))
            val result =  WallService.update(Post(3, Likes(count)))
            Assert.assertEquals(false, result)

        }



        @Test
        fun clearTest(){
            WallService.add(Post(1))
            WallService.add(Post(2))
            WallService.add(Post(3))
            WallService.clear()
            Assert.assertEquals(false, WallService.posts.isNotEmpty())

        }

        @Test
        fun addTest(){
            WallService.clear()
            WallService.add(Post(1))
            WallService.add(Post(2))
            Assert.assertEquals(true, WallService.posts.isNotEmpty())
        }




    }






