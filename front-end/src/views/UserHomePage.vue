<template>
  <v-card
    color="grey lighten-4"
    flat
    height="200px"
    tile
    style="margin-left: 10px; margin-right: 10px;"
  >
  <v-navigation-drawer
      id="core-navigation-drawer"
      v-model="drawer"
      :expand-on-hover="expandOnHover"
      app
      flat
      width="260"
    >
      <v-list nav expand>
        <v-list-item-group v-model="group">
          <template v-for="item in items">
            <v-list-item :key="item.title" @click="show(item.to)">
              <v-list-item-icon>
                <v-icon>{{ item.icon }}</v-icon>
              </v-list-item-icon>
              <v-list-item-title>{{ item.title }}</v-list-item-title>
            </v-list-item>
          </template>
        </v-list-item-group>
      </v-list>
    </v-navigation-drawer>
    <v-app-bar
      id="app-bar"
      app
      style="background-color: #00b2b2"
      
      height="75"
    >
      <v-btn
        class="mr-3"
        style="background-color: #00b2b2"
        elevation="0"
        fab
        small
        @click="drawer = !drawer"
      >
      <v-app-bar-nav-icon></v-app-bar-nav-icon>
      </v-btn>
      <v-toolbar-title>Unautentificated user</v-toolbar-title>
      <v-spacer></v-spacer>
       <v-btn text style="background-color: #00b2b2; " @click="login">
         <span>Login</span>
      </v-btn>
    </v-app-bar>

    <v-main fluid>
      <PharmaciesList v-if="show_menu==='pharmacies'"/>
      <MedicineList v-if="show_menu==='drugs'"/>
               
    </v-main>

  </v-card>
</template>

<script>
  import PharmaciesList from '../views/patient/PharmaciesList.vue'
  import MedicineList from '@/components/patient/MedicineList.vue'
  export default {
    name: "UserHomePage",
    components: {
      PharmaciesList,
      MedicineList,
    },
    props: {
    expandOnHover: {
      type: Boolean,
      default: false,
    },
  },
    data: () => ({
    drawer: false,
    group: null,
    show_menu:"pharmacies",
    items: [
      {
        icon: "mdi-home",
        title: "Home",
        to: "pharmacies",
      },
      {
        icon: "mdi-hospital-box",
        title: "Drugs",
        to: "drugs",
      },
    ],
  }),
  methods: {
    show(param){
      this.show_menu=param;
      this.drawer=false;
    },
    onScheduleClick:function(check){
      this.show_menu=check;
      this.drawer=false;
    },
    login:function(){
        console.log("fksalfk")
        this.$router.push('/login')
    }
  }
};
</script>