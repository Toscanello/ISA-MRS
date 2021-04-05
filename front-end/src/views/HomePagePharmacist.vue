<template>
  <div>
    <v-toolbar style="background-color: darkcyan">
      <v-app-bar-nav-icon @click="drawer = true"></v-app-bar-nav-icon>

      <v-toolbar-title>Pharmacist</v-toolbar-title>
    </v-toolbar>
    <v-navigation-drawer v-model="drawer" app temporary>
      <v-list nav dense>
        <v-list-item-group v-model="group">
          <v-list-item>
            <v-list-item-icon>
              <v-icon style="color: Tomato">mdi-home</v-icon>
            </v-list-item-icon>
            <v-list-item-title style="color: Tomato">Home</v-list-item-title>
          </v-list-item>

          <v-list-item>
            <v-list-item-icon>
              <v-icon style="color: Tomato">mdi-account</v-icon>
            </v-list-item-icon>
            <v-list-item-title style="color: Tomato">Account</v-list-item-title>
          </v-list-item>

          <v-list-item>
            <v-list-item-icon>
              <v-icon style="color: Tomato">mdi-pencil</v-icon>
            </v-list-item-icon>
            <v-list-item-title style="color: Tomato"
              >Start appointment</v-list-item-title
            >
          </v-list-item>

          <v-list-item>
            <v-list-item-icon>
              <v-icon style="color: Tomato">mdi-calendar</v-icon>
            </v-list-item-icon>
            <v-list-item-title style="color: Tomato">Calendar</v-list-item-title>
          </v-list-item>

          <v-list-item>
            <v-list-item-icon>
              <v-icon style="color: Tomato">mdi-minus</v-icon>
            </v-list-item-icon>
            <v-list-item-title style="color: Tomato">Dispensing drugs</v-list-item-title>
          </v-list-item>

          <v-list-item>
            <v-list-item-icon>
              <v-icon style="color: Tomato">mdi-plus</v-icon>
            </v-list-item-icon>
            <v-list-item-title style="color: Tomato">Vacation</v-list-item-title>
          </v-list-item>

          <v-list-item>
            <v-list-item-icon>
              <v-icon style="color: Tomato">mdi-plus</v-icon>
            </v-list-item-icon>
            <v-list-item-title style="color: Tomato">New appointment</v-list-item-title>
          </v-list-item>

        </v-list-item-group>
      </v-list>
    </v-navigation-drawer>
    <div class="id">
      <h1>Patients</h1>
    </div>
    <v-container>
    <v-row class="l-5">
      <v-col v-for="i in patients" :key="i.email" sm="6" lg="3">
        <v-card shaped>
          <v-list dense>
            <v-list-item>
                <v-list-item-content>Email:</v-list-item-content>
              <v-list-item-content class="align-end">
                {{ i.email }}
              </v-list-item-content>
            </v-list-item>
            <v-list-item>
              <v-list-item-content>Name:</v-list-item-content>
              <v-list-item-content class="align-end">
                {{ i.name }}
              </v-list-item-content>
            </v-list-item>
            <v-list-item>
              <v-list-item-content>Surname:</v-list-item-content>
              <v-list-item-content class="align-end">
                {{ i.surname }}
              </v-list-item-content>
            </v-list-item>
          </v-list>
        </v-card>
      </v-col>
    </v-row>
    </v-container>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "HomePagePharmacist",

  data: () => ({
    drawer: false,
    group: null,
    patients: [],
  }),
  created() {
    axios.get("http://localhost:9090/patients/derm@gmail.com").then((resp) => {
      this.patients = resp.data;
    });
  },
};
</script>

<style scoped>
div.id {
  height: 100px;
  width: 100%;
  background: rgb(19, 185, 185);
  display: flex;
  flex-direction: column;
  justify-content: center;
}
</style>