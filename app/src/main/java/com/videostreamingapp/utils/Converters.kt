//package com.newsapp.utils
//
//import androidx.room.TypeConverter
//import com.videostreamingapp.data.entities.Source
//import java.text.SimpleDateFormat
//
//class Converters {
//
//    // Room Source Converters
//    @TypeConverter
//    fun fromSource(source: Source) : String {
//        return source.name
//    }
//
//    @TypeConverter
//    fun toSource(name: String) : Source {
//        return Source(name, name)
//    }
//
//    companion object {
//        fun parseTimestamp(timestamp: String): String? {
//            val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
//            val outputFormat = SimpleDateFormat("yyyy-MM-dd")
//            val date = sdf.parse(timestamp)
//            return outputFormat.format(date)
//        }
//    }
//}