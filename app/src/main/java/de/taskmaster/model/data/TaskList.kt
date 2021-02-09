package de.taskmaster.model.data

import java.time.LocalDate

class TaskList {

    val title: String = ""

    /**
     * is null when no deadline is set
     */
    var deadline: LocalDate? = null

    val description: String = ""

    val tags: String = ""

    var repeat: Repeat = Repeat.NEVER

    /**
     * is null when not associated with any group
     */
    val group: Group? = null

    val status: Status = Status.OPEN

}