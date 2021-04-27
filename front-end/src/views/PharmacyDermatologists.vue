<template>
    <div>
        <pharmacy-admin-home>
        </pharmacy-admin-home>

        <v-card class="mx-auto" justify="center" style="width: 60%">
            <v-app-bar dark color="dark cyan" prominent>
                <v-toolbar-title>Zakazivanje pregleda</v-toolbar-title>
            </v-app-bar>
            <v-container>
                <v-row dense>
                    <v-col cols="12"
                        v-for="dermatologist in dermatologists"
                        :key="dermatologist.email">
                        <v-card>
                            <div class="d-flex flex-no-wrap justify-space-between">
                                <div>
                                    <v-card-title
                                    class="text-h5"
                                    v-text="dermatologist.name + ' ' + dermatologist.surname"></v-card-title>

                                    <v-card-subtitle class="text-h6" v-text="dermatologist.email"></v-card-subtitle>

                                    <v-card-actions>
                                        <v-btn depressed @click="goToAppointment(dermatologist.email)">Zakazi pregled</v-btn>
                                    </v-card-actions>
                                </div>
                            </div>
                        </v-card>
                    </v-col>
                </v-row>
            </v-container>
        </v-card>
    </div>
</template>

<script>
import axios from 'axios'
import PharmacyAdminHome from './PharmacyAdminHome.vue'
export default {
    components: { PharmacyAdminHome },
    name: 'PharmacyDermatologists',
    data () {
        return {
            dermatologists: null
        }
    },
    methods: {
        goToAppointment: function(email) {
            let regNo = this.$route.params.regNo
            this.$router.push('/dermatologists/' + email + '/' + regNo + '/appointment')
        }
    },
    mounted() {
        let path = "http://localhost:9090/api/dermatologists/pharmacy/" + this.$route.params.regNo
        axios
        .get(path)
        .then(response => {
            this.dermatologists = response.data
            console.log(JSON.stringify(this.dermatologists))
            })
        .catch(error => {
            alert('Error: status ' + error.response.status)
            this.$router.push('/')
        })
    }
}
</script>

<style>
    .dermatologist-card {
        border: 2px solid darkcyan;
        border-top-left-radius: 15px;
        border-bottom-right-radius: 15px;
        color: darkcyan;
        padding: 15px;
        margin: 15px;
        width: 50%;
    }

    .dermatologist-card h3 {
        padding-bottom: 20px;
        font-weight: bold;
    }
</style>
