package de.taskmaster.model.data

class User : ObservableViewModel() {

    var username: String? = "TheYaINN"

    var password: String? = null

    var firstName: String? = "Bengt"

    var lastName: String? = "Joachimsohn"

    var email: String? = "bengt@joachimsohn.de"

    var rememberMe: Boolean = false

    var groups: List<Group> = arrayListOf(Group().apply { title = "Group 1" }, Group().apply { title = "Group 2" })

    var lists: List<TaskList> =
        arrayListOf(TaskList().apply {
            tasks = arrayListOf(Task(), Task())
            title = "Test titel"
        }, TaskList().apply { title = "Test titel" })

    val places: MutableList<Address> = arrayListOf(Address(), Address(), Address(), Address())
}