package de.taskmaster.model.binding

interface ListEditorContract {

    interface Presenter {
        fun toggleView(view: android.view.View)
        fun setDate(view: android.view.View)
    }

    interface View {
        fun getLayoutView(): View
        fun setDate(daysAhead: Int)
    }

}