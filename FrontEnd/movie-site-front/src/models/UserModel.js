import axios from "axios";

export default class UserModel {
    constructor(email, password, age, name) {
        this.email = email
        this.password = password
        this.age = age
        this.name = name
    }

    login() {
        return axios
            .post(process.env.VUE_APP_SERVER_URL + "/api/users/user", this)
    }

    register() {
        return axios
            .post(process.env.VUE_APP_SERVER_URL + "/api/users", this)
    }
}