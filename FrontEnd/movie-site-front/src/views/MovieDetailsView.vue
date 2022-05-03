<template>
  <div>
    <ul>
      <li id="first">
        <img :src="movie.imageURL"/>
      </li>

      <li id="second">
        <h1>{{ movie.name }}</h1>
        <br/>
        <p><b>Genre:</b> {{ movie.genre }}</p>
        <p><b>Release Year:</b> {{ movie.releaseYear }}</p>
        <p>
          <b>Director: </b>
          {{ movie.director.name }}
        </p>
        <img
            @click="redirectToDirector(movie.director.id)"
            class="director"
            :src="movie.director.imageURL"
            style="width: 100px; height: 150px"
        />
        <p><b>Description: </b>{{ movie.description }}</p>
      </li>
    </ul>

    <h2 style="padding-left: 50px">
      Actors that played in <u>"{{ movie.name }}"</u>
    </h2>
    <ul>
      <li v-for="m in movie.actedInActors" :key="m.id">
        <div class="gallery">
          <h2>{{ m.name }}</h2>
          <img :src="m.imageURL" @click="redirectToActor(m.id)"/>
        </div>
      </li>
    </ul>

    <h2 style="padding-left: 50px"><u>All the reviews</u></h2>
    <div id="second" v-if="areReviews">
      <li
          v-for="user in getUsersReviews"
          :key="user.id"
          style="list-style-type: none"
      >
        <b>{{ user.userDTO.name }}</b
        ><br/>
        {{ user.content }}
      </li>
    </div>
    <v-text-field
        style="padding-left: 25px; padding-right: 100px"
        label="Your review"
        v-model="reviewContentToAdd"
    >
    </v-text-field>
    <v-btn @click="addMovieReview()" color="info" class="mx-4"
    >Add review
    </v-btn
    >
    <v-btn
        v-if="userHasReview"
        @click="deleteMovieReview()"
        color="error"
        class="mx-4"
    >Delete your review
    </v-btn
    >
    <br/>
    <b>Disclaimer: </b> If you already reviewed this movie, adding a new review
    will update your current one
  </div>
</template>

<style scoped>
#first {
  margin: 30px;
  width: 300px;
  display: inline-table;
  text-align: center;
  float: left;
}

#second {
  margin: 30px;
  width: 900px;
  display: inline-table;
  border-radius: 25px;
  border: 2px solid #73ad21;
  padding: 20px;
  float: inline-start;
  overflow: hidden;
}
</style>

<script>
import axios from "axios";
import MovieModel from "@/models/MovieModel";

export default {
  name: "movieDetails",
  data() {
    return {
      movie: null,
      reviews: [],
      reviewContentToAdd: null,
      areReviews: false,
      userHasReview: false,
      movieModel: new MovieModel(),
    };
  },
  created() {
    this.movie = this.$route.params.movie;
    this.reviews = this.movie.reviewedByUsers;
    if (this.reviews.length > 0) {
      this.areReviews = true;
    } else {
      this.areReviews = false;
    }
    this.reviews.forEach((review) => {
      if (review.userDTO.name === localStorage.getItem("username")) {
        this.userHasReview = true;
      }
    });
  },
  methods: {
    redirectToDirector(directorId) {
      console.log(directorId);
    },
    redirectToActor(actorId) {
      console.log(actorId);
    },
    addMovieReview() {
      if (this.reviewContentToAdd !== null) {
        this.movieModel
            .addMovieReview(
                this.movie.id,
                localStorage.getItem("email"),
                this.reviewContentToAdd
            )
            .then((response) => {
              alert(response.data);
              this.reviewContentToAdd = null;
              this.$router.push({name: "home"});
            });
      } else {
        alert("Your review content is empty!");
      }
    },
    deleteMovieReview() {
      const url =
          process.env.VUE_APP_SERVER_URL +
          "/api/review/" +
          this.movie.id +
          "/" +
          localStorage.getItem("email");
      this.movieModel
          .deleteMovieReview(this.movie.id, localStorage.getItem("email"))
          .then((response) => {
            alert(response.data);
            this.userHasReview = false;
            this.$router.push({name: "home"});
          });
    },
  },
  computed: {
    getUsersReviews() {
      return this.reviews;
    },
  },
};
</script>
