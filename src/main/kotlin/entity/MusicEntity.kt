package entity

class MusicEntity (
    val genre:String,
    val name: String,
    val author: String,
    val level: MutableMap<String, Int>
)
{
    override fun toString(): String {
        return "$genre  | $name | $author | $level"
    }
}