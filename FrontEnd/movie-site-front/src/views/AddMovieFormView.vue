<template>
  <div>
    <h2>Adding a new movie</h2>
    <br/>
    <v-form class="mx-4" ref="form" v-model="valid" lazy-validation>
      <v-text-field
          v-model="movie.genre"
          :rules="nameRules"
          label="Genre"
          required
      ></v-text-field>

      <v-text-field
          v-model="movie.name"
          :rules="nameRules"
          label="Name"
          required
      ></v-text-field>

      <v-text-field
          v-model="movie.releaseYear"
          :rules="releaseYearRules"
          label="Release year"
          required
      ></v-text-field>

      <v-select
          v-model="selectDirector"
          :items="directors"
          item-text="name"
          item-value="name"
          :rules="[(v) => !!v || 'Item is required']"
          label="Directed by"
          required
      ></v-select>

      <v-btn :disabled="!valid" color="success" class="mr-4" @click="addMovie">
        Add movie
      </v-btn>

      <v-btn :disabled="!valid" color="info" class="mr-4" @click="updateMovie">
        Update movie
      </v-btn>

      <v-btn color="error" class="mr-4" @click="resetForm"> Reset Form</v-btn>
    </v-form>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "Home",
  data() {
    return {
      bctx: "http://localhost:8080",
      fctx: window.location.protocol + "//" + window.location.host,
      valid: true,
      movie: {
        id: 0,
        name: "",
        genre: "",
        releaseYear: "",
        director: null,
        actedInActors: [],
        reviewedByUsers: [],
      },
      selectDirector: null,
      directors: [],
      nameRules: [(v) => !!v || "This field is required"],
      releaseYearRules: [
        (v) => !!v || "This field is required",
        (v) =>
            (v && v >= 1888) ||
            "The release year of the movie cannot be earlier than 1888",
      ],
    };
  },
  created() {
    try {
      const movie = this.$route.params.movie;
      (this.movie.id = movie.id), (this.movie.name = movie.name);
      this.movie.genre = movie.genre;
      this.movie.releaseYear = movie.releaseYear;
      this.selectDirector = movie.director.name;
    } catch {
      this.resetForm();
    }
    axios.get(this.bctx + "/api/directors").then((response) => {
      for (let i = 0; i < response.data.length; i++) {
        this.directors.push(response.data[i]);
        if (this.selectDirector === response.data[i].name) {
          this.movie.director = this.directors[i];
        }
      }
    });
  },
  methods: {
    updateMovie() {
      axios
          .patch(this.bctx + "/api/movies/" + this.movie.id, this.movie)
          .then((response) => {
            alert("Movie updated");
            this.$router.push({name: "home"});
          })
          .catch((error) => alert(error));
    },
    validate() {
      return this.$refs.form.validate();
    },
    addMovie: function () {
      if (this.validate()) {
        const movie = {
          name: this.movie.name,
          genre: this.movie.genre,
          releaseYear: this.movie.releaseYear,
          director: null,
          actedInActors: [],
          reviewedByUsers: [],
        };
        axios
            .post(
                this.bctx + "/api/movies?director=" + this.selectDirector,
                movie
            )
            .then(() => {
              alert(this.movie.name + " was added");
              this.$router.push({name: "home"});
            })
            .catch(() => alert("This movie already exists"));
      }
    },
    resetForm: function () {
      this.movie.genre = "";
      this.movie.name = "";
      this.movie.releaseYear = "";
      this.selectDirector = null;
    },
  },
  watch: {
    selectDirector() {
      for (let i = 0; i < this.directors.length; i++) {
        if (this.selectDirector === this.directors[i].name) {
          this.movie.director = this.directors[i];
          console.log(this.movie.director);
        }
      }
    },
  },
};
</script>
