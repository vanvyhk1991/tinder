package com.app.tinder.model

/**
 * {
 *     "url": "https://s-media-cache-ak0.pinimg.com/736x/de/87/7b/de877bcccc2295a58fe8758fee0ebc7d.jpg",
 *     "name": "Jainis",
 *     "age": 24,
 *     "location": "New York"
 *   }
 */
data class Profile(
    val name: String,
    val age: Int,
    val url: String,
    val location: String,
    var statusMove: StatusMove? = StatusMove.NONE
)

enum class StatusMove{
    LEFT, RIGHT, NONE
}