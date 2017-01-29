/**
 * Created by SAMSUNG on 28.1.2017.
 */
var Sequelize = require('sequelize');
var PrescriptionType = require('./models/PRESCRIPTION_TYPE');

var sequelize = new Sequelize('TRDatabase', 'root', '4596ak69', {
    host: '178.62.223.153',
    port: 3306,
    dialect: 'mysql'
});
sequelize.authenticate()
    .then(function () {
        console.log("CONNECTED! ");
    })
    .catch(function (err) {
        console.log('not CONNECTED');
    })
    .done();

sequelize.models.PRESCRIPTION_TYPE.findById().then(function (user) {
    console.log(user.get('firstName'));
});
