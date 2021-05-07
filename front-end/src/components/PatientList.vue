<template>
    <v-container>
        <v-toolbar style="margin-bottom: 10px;">
        <PatientSearch id="search" @clicked="onSearchClick" />
        <PatientSort id="search" @clicked="onSortClick" />
      </v-toolbar>
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
</template>

<script>
import axios from 'axios'
import PatientSearch from "@/components/PatientSearch.vue";
import PatientSort from "@/components/PatientSort.vue";
export default {
  name: "PatientList",
  components:{
    PatientSearch,
    PatientSort
  },
  data: () => ({
    patients:[]
  }),
  created() {
    let kor = localStorage.getItem('korisnik');
    console.log(kor);
    axios.get(`http://localhost:9090/patients/${kor}@gmail.com`).then((resp) => {
      this.patients = resp.data;
    });
  },
  methods:{
      onSearchClick: function (search) {
      console.log(search);
      let kor = localStorage.getItem('korisnik');
      axios
        .get("http://localhost:9090/patients/search", {
          params: {
            email: `${kor}@gmail.com`,
            name: search.name,
            surname: search.surname,
          },
        })
        .then((response) => (this.patients = response.data));
    },
    onSortClick: function (sorting) {
      console.log(sorting);
      if (sorting == "NameAsc") {
        this.patients.sort(function (a, b) {
          if (a.name < b.name) {
            return -1;
          }
          if (a.name > b.name) {
            return 1;
          }
          return 0;
        });
      } else if (sorting == "NameDesc") {
        this.patients.sort(function (a, b) {
          if (a.name < b.name) {
            return 1;
          }
          if (a.name > b.name) {
            return -1;
          }
          return 0;
        });
      } else if (sorting == "SurnameAsc") {
        this.patients.sort(function (a, b) {
          if (a.surname < b.surname) {
            return -1;
          }
          if (a.surname > b.surname) {
            return 1;
          }
          return 0;
        });
      } else if (sorting == "SurnameDesc") {
        this.patients.sort(function (a, b) {
          if (a.surname < b.surname) {
            return -1;
          }
          if (a.surname > b.surname) {
            return 1;
          }
          return 0;
        });
      }
    },
  }
};
</script>