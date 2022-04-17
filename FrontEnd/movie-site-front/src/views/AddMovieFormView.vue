<template>
  <div>
    <h2>Adding a new movie</h2>
    <br/>
    <v-form class="mx-4" ref="form" v-model="valid" lazy-validation>
      <v-text-field
          v-model="movieModel.genre"
          :rules="nameRules"
          label="Genre"
          required
      ></v-text-field>

      <v-text-field
          v-model="movieModel.name"
          :rules="nameRules"
          label="Name"
          required
      ></v-text-field>

      <v-text-field
          v-model="movieModel.releaseYear"
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
import MovieModel from "@/models/MovieModel";

export default {
  name: "Home",
  data() {
    return {
      valid: true,
      movieModel: new MovieModel(),
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
      this.movieModel.setId(movie.id);
      this.movieModel.setName(movie.name);
      this.movieModel.setGenre(movie.genre);
      this.movieModel.setReleaseYear(movie.releaseYear);
      this.movieModel.setDirector(movie.director.name);
      this.selectDirector = movie.director.name;
    } catch {
      this.resetForm();
    }
    axios
        .get(process.env.VUE_APP_SERVER_URL + "/api/directors")
        .then((response) => {
          for (let i = 0; i < response.data.length; i++) {
            this.directors.push(response.data[i]);
            if (this.selectDirector === response.data[i].name) {
              this.movieModel.director = this.directors[i];
            }
          }
        });
  },
  methods: {
    addMovie() {
      if (this.validate()) {
        this.movieModel
            .createMovieModel()
            .then(() => {
              alert(this.movieModel.name + " was added");
              this.$router.push({name: "home"});
            })
            .catch((error) => {
              console.log(error);
              alert("This movie already exists");
            });
      }
    },
    updateMovie() {
      this.movieModel
          .updateMovieModel()
          .then((response) => {
            alert("Movie updated");
            this.$router.push({name: "home"});
          })
          .catch((error) => alert(error));
    },
    validate() {
      return this.$refs.form.validate();
    },
    resetForm() {
      this.movieModel.genre = "";
      this.movieModel.name = "";
      this.movieModel.releaseYear = "";
      this.selectDirector = null;
    },
  },
  watch: {
    selectDirector() {
      for (let i = 0; i < this.directors.length; i++) {
        if (this.selectDirector === this.directors[i].name) {
          this.movieModel.director = this.directors[i];
        }
      }
    },
  },
};
</script>
