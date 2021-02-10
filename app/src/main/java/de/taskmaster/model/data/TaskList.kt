package de.taskmaster.model.data

class TaskList {

    val title: String = ""

    /**
     * is null when no deadline is set
     */
    var deadline: DeadLine? = DeadLine()

    var place: Address? = null

    val description: String = ""

    val tags: String = ""

    var repeat: Repeat = Repeat.NEVER

    /**
     * is null when not associated with any group
     */
    var group: Group? = null

    val status: Status = Status.OPEN

}