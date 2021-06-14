<template>
    <div class="align-items-center">
        <pharmacy-admin-home></pharmacy-admin-home>
        <v-container>
            <v-row>
                <v-col cols="12">
        <v-data-table
            :headers="headers"
            :items="medicine"
            class="elevation-1"
        >
            <template v-slot:top>
                <v-toolbar flat>
                    <v-toolbar-title>Dostupni Lekovi</v-toolbar-title>
                    <v-divider class="mx-4" inset vertical></v-divider>
                    <v-spacer></v-spacer>
                    <input type="date" v-model="dueDate">
                    <v-spacer></v-spacer>
                    <v-btn color="primary" dark class="mb-2"
                        v-bind="attrs" v-on="order" @click="publishOrder">
                        Order
                    </v-btn>
                    <v-dialog v-model="dialog" max-width="500px">
                        <v-card>
                            <v-card-title>
                                <span class="headline"> Add item </span>
                            </v-card-title>

                            <v-card-text>
                                <v-container>
                                    <v-row>
                                        <v-col cols="12" sm="6" md="6">
                                            <v-text-field v-model="amount" label="Medicine amount"></v-text-field>
                                        </v-col>
                                    </v-row>
                                </v-container>
                            </v-card-text>

                            <v-card-actions>
                                <v-spacer></v-spacer>
                                <v-btn color="blue darken-1" text @click="close">
                                    Cancel
                                </v-btn>
                                <v-btn color="blue darken-1" text @click="add">
                                    Add
                                </v-btn>
                            </v-card-actions>
                        </v-card>
                    </v-dialog>  
                </v-toolbar>
            </template> 
            
            <template v-slot:item.actions="{ item }">
                <v-icon small class="mr-2" @click="addItem(item)">
                    mdi-plus
                </v-icon>
            </template>
        </v-data-table>
        <br />
        <hr />
        <div v-for="item in namedOrder" :key="item.code" style="text-align: left; padding: 20px;"> 
            <h3>{{item.name}}</h3>
            <p>Amount: {{item.amount}}</p>
            <br />
        </div>
                </v-col>
            </v-row>
        </v-container>
    </div>
</template>

<script>
import axios from 'axios'
import PharmacyAdminHome from '../PharmacyAdminHome.vue'
import authHeader from '../../services/auth-header'
export default {
  components: { PharmacyAdminHome },
    name: 'AllMedicineTable',
    data () {
        return {
            dialog: false,
            headers: [
                {text: 'Name', value: 'name'},
                {text: 'Manufacturer', value: 'manufacturer'},
                {text: 'Form', value: 'drugForm'},
                {text: 'Add item', value: 'actions'}
            ],
            addedIndex: -1,
            medicine: [],
            amount: 0,
            itemToAdd: null,
            order: [],
            dueDate: ''
        }
    },
    computed: {
        namedOrder() {
            let namedItems = []
            for (let item of this.order) {
                for (let med of this.medicine) {
                    if (item.code === med.code) {
                        namedItems.push({name: med.name, amount: item.amount})
                    }
                }
            }
            return namedItems
        },
        adaptedOrder() {
            let adaptedItems = []
            for (let item of this.order) {
                adaptedItems.push({
                    medicineCode: item.code,
                    amount: item.amount
                })
            }
            return adaptedItems
        }
    },
    watch: {
        dialog(val) {
            val || this.close()
        }
    },
    mounted() {
        axios
        .get("http://localhost:9090/api/medicine/findall")
        .then(response => {
            this.medicine = response.data
        })
    },
    methods: {
        addItem(item) {
            this.itemToAdd = item
            this.dialog = true
        },
        close () {
            this.dialog = false
        },
        add() {
            console.log('A D D')
            console.log(this.amount)
            alert('Adding ' + this.amount + ' of ' + this.itemToAdd.name)
            
            let containsAddedItem = false
            for (let item of this.order) {
                if (item.code === this.itemToAdd.code) {
                    item.amount = Number(this.amount) + Number(item.amount)
                    containsAddedItem = true
                }
            }
            if (!containsAddedItem) {
                this.order.push({
                    code: this.itemToAdd.code,
                    amount: this.amount
                })
            }
            this.amount = 0
            this.itemToAdd = null
            this.dialog = false
        },
        publishOrder() {
            console.log(this.order)
            if (this.dueDate === '')
                alert('Select a valid date')
            else if (this.order.length == 0)
                alert('Order is empty')
            else {
                alert('Order scheduled for: ' + this.dueDate)
                let newOrder = {
                        id: -1, 
                        pharmacyRegNo: this.$route.params.regNo,
                        dueDate: this.dueDate, 
                        bulkOrderItemList: this.adaptedOrder
                    }
                axios
                .post(
                    'http://localhost:9090/api/order/new', newOrder,
                    { headers: authHeader() }
                )
                .then(response => {
                    alert('Order successfully processed!')
                    console.log(response)
                })
                .catch(error => {
                    alert(error.response.data)
                })
                this.$router.push('/pharmacyadmin')
            }
        }
    }
}
</script>

<style>

</style>