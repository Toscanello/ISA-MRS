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
            <v-list-item-title style="color: Tomato"
              >Calendar</v-list-item-title
            >
          </v-list-item>

          <v-list-item>
            <v-list-item-icon>
              <v-icon style="color: Tomato">mdi-minus</v-icon>
            </v-list-item-icon>
            <v-list-item-title style="color: Tomato"
              >Dispensing drugs</v-list-item-title
            >
          </v-list-item>

          <v-list-item>
            <v-list-item-icon>
              <v-icon style="color: Tomato">mdi-plus</v-icon>
            </v-list-item-icon>
            <v-list-item-title style="color: Tomato"
              >Vacation</v-list-item-title
            >
          </v-list-item>

          <v-list-item>
            <v-list-item-icon>
              <v-icon style="color: Tomato">mdi-plus</v-icon>
            </v-list-item-icon>
            <v-list-item-title style="color: Tomato"
              >New appointment</v-list-item-title
            >
          </v-list-item>
        </v-list-item-group>
      </v-list>
    </v-navigation-drawer>
    <div class="id">
      <h1>Patients</h1>
    </div>
    <v-container>
      <PatientSearch id="search" @clicked="onSearchClick" />
      <PatientSort id="search" @clicked="onSortClick" />
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
import PatientSearch from '@/components/PatientSearch.vue'
import PatientSort from '@/components/PatientSort.vue'

export default {
  name: "HomePagePharmacist",
  components:{
    PatientSearch,
    PatientSort
  },
  data: () => ({
    
    drawer: false,
    group: null,
    patients: []
    
  }),
  created() {
    axios.get("http://localhost:9090/patients/farm@gmail.com").then((resp) => {
      this.patients = resp.data;
    });
  },
  methods:{
    onSearchClick:function(search){
      console.log(search)
      axios
      .get("http://localhost:9090/patients/search",{
        params:{
          email:"farm@gmail.com",
          name:search.name,
          surname:search.surname
        }
      })
      .then(response=>this.patients = response.data)
    },
    onSortClick:function(sorting){
      console.log(sorting)
      if(sorting == "NameAsc"){
        this.patients.sort(function(a, b){
           if(a.name < b.name) { return -1; }
          if(a.name > b.name) { return 1; }
          return 0;
        })
      }else if(sorting == "NameDesc"){
        this.patients.sort(function(a, b){
           if(a.name < b.name) { return 1; }
          if(a.name > b.name) { return -1; }
          return 0;
        })
      }else if(sorting == "SurnameAsc"){
        this.patients.sort(function(a, b){
           if(a.surname < b.surname) { return -1; }
          if(a.surname > b.surname) { return 1; }
          return 0;
        })
      }else if(sorting == "SurnameDesc"){
        this.patients.sort(function(a, b){
           if(a.surname < b.surname) { return -1; }
          if(a.surname > b.surname) { return 1; }
          return 0;
        })
      }
    }
  }
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