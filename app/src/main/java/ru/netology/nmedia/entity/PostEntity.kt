package ru.netology.nmedia.entity

import androidx.room.*
import ru.netology.nmedia.dto.Attachment
import ru.netology.nmedia.dto.Post

@Entity
data class PostEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val author: String,
    val authorAvatar: String,
    val content: String,
    val published: String,
    val likedByMe: Boolean,
    val likes: Int = 0,
    val shares: Int = 0,
    val videoUrl: String?,
    var isRead: Boolean = false,
    @Embedded
    val attachment: Attachment? = null,
) {
    fun toDto() = Post(
        id,
        author,
        authorAvatar,
        content,
        published,
        likedByMe,
        likes,
        shares,
        videoUrl,
        isRead,
        attachment
    )

    companion object {
        fun fromDto(dto: Post) =
            PostEntity(
                dto.id,
                dto.author,
                dto.authorAvatar,
                dto.published,
                dto.content,
                dto.likedByMe,
                dto.likes,
                dto.shares,
                dto.videoUrl,
                dto.isRead,
                dto.attachment
            )

    }
}

fun List<PostEntity>.toDto(): List<Post> = map(PostEntity::toDto)
fun List<Post>.toEntity(): List<PostEntity> = map(PostEntity::fromDto)

//data class Attachment(
//    var url: String,
//    val description: String,
//    val type: String
//) {
//    fun toDto() = Attachment(url, description, type)
//
//    companion object {
//        fun fromDto(dto: Attachment?) = dto?.let {
//            Attachment(it.url, it.description, it.type)
//        }
//    }
//}

class AttachmentConverter {
    @TypeConverter
    fun fromAttachment(attachment: Attachment): String {
        return attachment.url
    }

    @TypeConverter
    fun toAttachment(attachUrl: String) {
        //TODO Make Later
    }
}