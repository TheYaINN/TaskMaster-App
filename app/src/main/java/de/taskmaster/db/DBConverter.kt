package de.taskmaster.db


//TODO: used for DB Convertion
class DBConverter {

    /**
     * Example of how a converter should work

    @TypeConverter
    fun toCalendar(date: String?): Calendar? {
    val cal = Calendar.getInstance()
    val sdf = SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH)
    try {
    cal.time = sdf.parse(date)
    } catch (e: ParseException) {
    e.printStackTrace()
    }
    return cal
    }

    @TypeConverter
    fun fromCalendar(date: Calendar): String? {
    return SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy").format(date.time)
    }*/

}
