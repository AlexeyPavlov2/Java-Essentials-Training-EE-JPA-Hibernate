class Customer {
    constructor(id, name, address, city, phone, mail, site, type) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.city = city;
        this.phone = phone;
        this.mail = mail;
        this.site = site;
        this.type = type;
    }

    toString() {
        return 'Customer: ' + '{' + 'id: ' + this.id + ' name: ' + this.name + ' address: ' + this.address + ' city: ' + this.city
                + ' phone: ' + this.phone + ' mail: ' + this.mail + ' site: ' + this.site + ' type: ' + this.type;
    }
}

var app = new Vue({
    el: '#app',
    data: {
        customerList: [],
        visible: false,
        editCustomer: null,
        cashedCustomer: {},
        addMode: false,
        newRecord: {}
    },

    methods: {
        setAddMode(value) {
            this.addMode = value;


        },
        saveNewCustomer(item) {
            console.log('saveNewCustomer');
            if (document.getElementById("input-name").value === "" ||
                    document.getElementById("input-address").value === "" ||
                    document.getElementById("input-phone").value === "" ||
                    document.getElementById("input-city").value === "" ||
                    document.getElementById("input-mail").value === "" ||
                    document.getElementById("input-site").value === "" ||
                    document.getElementById("input-type").value === "") {
                alert("Input field can not be empty. Please enter a value.");
                return;
            }
            this.$data.visible = true;

            this.setAddMode(false);
            item.id = 0;
            //save to database
            axios.post('GetData', JSON.stringify({
                mode: 'CreateCustomer',
                customer: {id: item.id, name: item.name, address: item.address, city: item.city, phone: item.phone, mail: item.mail, site: item.site, type: item.type}
            }), {
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded;charset=utf-8"
                }
            }).then(function (response) {
                item.id = response.data.customer.id;
                app.customerList.push(item);
                return response;
            });




            //clear cash record
            this.newRecord = {};
            this.$data.visible = false;
        },
        notSaveNewRecord(item) {
            this.setAddMode(false);
            this.newRecord = {};
        },
        doEdit(item, i) {
            this.editCustomer = item.id;
            //do cash of current record for UNDO
            this.cachedCustomer = {id: item.id, name: item.name, address: item.address, city: item.city, phone: item.phone, mail: item.mail, site: item.site, type: item.type};
            document.getElementById("add_btn").disabled = true;
        },
        deleteCustomer(id) {
            axios.post('GetData', JSON.stringify({
                mode: 'DeleteCustomer',
                id: id
            }), {
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded;charset=utf-8"
                }
            }).then(function (response) {
                var length = app.customerList.length;
                var index = app.customerList.map(function (x) {
                    return x.id;
                }).indexOf(id);
                app.customerList.splice(index, 1);
                return response;
            });
        },
        updateCustomer(item, i) {
            document.getElementById("add_btn").disabled = false;
            this.cashedCustomer = item;
            this.editCustomer = null;
            axios.post('GetData', JSON.stringify({
                mode: 'UpdateCustomer',
                customer: {id: item.id, name: item.name, address: item.address, city: item.city, phone: item.phone, mail: item.mail, site: item.site, type: item.type}
            }), {
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded;charset=utf-8"
                }
            }).then(function (response) {
                return response;
            });


        },
        notUpdateCustomer(item, i) {
            this.editCustomer = null;
            document.getElementById("add_btn").disabled = false;
            //undo
            this.customerList[i].id = this.cachedCustomer.id;
            this.customerList[i].name = this.cachedCustomer.name;
            this.customerList[i].address = this.cachedCustomer.address;
            this.customerList[i].city = this.cachedCustomer.city;
            this.customerList[i].phone = this.cachedCustomer.phone;
            this.customerList[i].mail = this.cachedCustomer.mail;
            this.customerList[i].site = this.cachedCustomer.site;
            this.customerList[i].type = this.cachedCustomer.type;
            return this.cashedCustomer;
        },
        getCustomer(id, event) {
            axios.post('GetData', JSON.stringify({
                mode: 'GetCustomer',
                id: id
            }), {
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded;charset=utf-8"
                }
            }).then(function (response) {
                return response.data.customers[0];
            });
        },
        getAllCustomer(event) {
            var customers = [];
            axios.post('GetData', JSON.stringify({
                mode: 'GetAllCustomer',
                id: '0'
            }), {
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded;charset=utf-8"
                }
            }).then(function (response) {
                for (var i = 0; i < response.data.customers.length; i++) {
                    customers.push(
                            new Customer(response.data.customers[i].id, response.data.customers[i].name,
                                    response.data.customers[i].address, response.data.customers[i].city,
                                    response.data.customers[i].phone, response.data.customers[i].mail,
                                    response.data.customers[i].site, response.data.customers[i].type));
                }
            });
            return customers;
        }
    },

    created: function () {
        this.$data.editMode = false;
        this.$data.visible = true;
        this.customerList = this.getAllCustomer();
        this.$data.visible = false;
    }
});
