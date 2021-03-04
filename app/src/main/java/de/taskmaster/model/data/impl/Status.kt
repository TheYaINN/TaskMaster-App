package de.taskmaster.model.data.impl

import de.taskmaster.R

enum class Status(val resID: Int) {
    FINISHED(R.drawable.check),
    OPEN(R.drawable.clear)
}
