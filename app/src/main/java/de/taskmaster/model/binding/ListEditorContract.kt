package de.taskmaster.model.binding

interface ListEditorContract {

    interface Presenter {
        fun toggleView(view: android.view.View)
    }

    interface View {
        fun toggle(caller: Int)
    }

}