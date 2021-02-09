package de.taskmaster.model.data

import java.time.LocalDate

class TaskList {

    val title: String = "some title"

    /**
     * is null when no deadline is set
     */
    var deadline: LocalDate? = null

    val description: String = "Some long description that does not fit the screen"

    val tags: String = ""

    var repeat: Repeat = Repeat.NEVER

    /**
     * is null when not associated with any group
     */
    val group: Group? = null

    val status: Status = Status.OPEN

}