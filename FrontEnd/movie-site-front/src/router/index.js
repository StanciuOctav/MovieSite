import Vue from 'vue'
import VueRouter from 'vue-router'
import LoginView from '../views/LoginView.vue'
import Register from '../views/Register.vue'
import HomeView from '../views/HomeView.vue'
import AddMovieFormView from '../views/AddMovieFormView.vue'
import MovieDetailsView from '../views/MovieDetailsView.vue'
import LogoutView from '../views/LogoutView.vue'
import DirectorDetailsView from '../views/DirectorDetailsView.vue'

Vue.use(VueRouter)

const routes = [
    {
        path: '/login',
        name: 'login',
        component: LoginView
    },
    {
        path: '/logout',
        name: 'logout',
        component: LogoutView
    },
    {
        path: '/register',
        name: 'register',
        component: Register
    },
    {
        path: '/home',
        name: 'home',
        component: HomeView
    },
    {
        path: '/addMovie',
        name: 'addMovie',
        component: AddMovieFormView
    },
    {
        path: '/movieDetails',
        name: 'movieDetails',
        component: MovieDetailsView
    },
    {
        path: '/directorDetails',
        name: 'directorDetails',
        component: DirectorDetailsView
    },
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

export default router
