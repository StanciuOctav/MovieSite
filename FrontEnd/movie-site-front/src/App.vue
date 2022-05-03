<template>
  <v-app>
    <v-app-bar app color="primary" dark>
      <h1>Movie Site</h1>
      <div class="d-flex align-center" style="margin-right: auto">
        <v-btn
            v-if="loggedIn"
            @click="addNewMovie"
            class="my-4 mx-4"
            color="success"
            rounded
        >
          <v-icon left> mdi-pencil</v-icon>
          Add new Movie
        </v-btn>
      </div>

      <v-btn v-if="loggedIn" @click="redirectHome()" rounded>
        <v-icon>mdi-home</v-icon>
        Home
      </v-btn>

      <div v-if="loggedIn">
        <v-menu
            v-for="([text, rounded], index) in btns"
            :key="text"
            :rounded="rounded"
            offset-y
        >
          <template v-slot:activator="{ attrs, on }">
            <v-btn
                :color="colors[index]"
                class="white--text ma-5"
                v-bind="attrs"
                v-on="on"
            >
              <v-icon> mdi-account</v-icon>
              Profile
            </v-btn>
          </template>

          <v-list>
            <v-list-item v-for="(item, index) in items" :key="index" link>
              <v-list-item-title
                  @click="showClicked(item.title)"
                  v-text="item.title"
              ></v-list-item-title>
            </v-list-item>
          </v-list>
        </v-menu>
      </div>
    </v-app-bar>

    <v-main>
      <router-view/>
    </v-main>
  </v-app>
</template>

<script>
import LoginView from "@/views/LoginView.vue";
export default {
  name: "App",
  components: {
    LoginView,
  },
  data: () => ({
    btns: [["Custom", "b-xl"]],
    colors: ["deep-purple accent-4"],
    items: [
      {title: "Watchlist"},
      {title: "Account details"},
      {title: "Logout"},
    ],
    currUrl: window.location.href,
    loggedIn: true,
  }),
  created() {
    if (this.currUrl === "http://localhost:8081/") {
      this.redirectLogin();
    }
  },
  mounted() {
    this.$root.$on("changeLoggedIn", () => {
      this.updateLogin();
    });
  },
  methods: {
    // Profile dropdown
    showClicked(name) {
      if (name === "Logout") {
        this.redirectLogout();
      }
      if (name === "Profile details") {
        console.log("Redirecting to profile page");
      }
      if (name === "Watchlist") {
        console.log("Redirecting to watchlist");
      }
    },
    updateLogin() {
      this.loggedIn = !this.loggedIn;
    },
    redirectLogin() {
      this.$router.push({name: "login"});
    },
    redirectHome() {
      this.$router.push({name: "home"});
    },
    addNewMovie() {
      this.$router.push({name: "addMovie"});
    },
    redirectLogout() {
      this.$router.push({name: "logout"});
    },
  },
};
</script>
