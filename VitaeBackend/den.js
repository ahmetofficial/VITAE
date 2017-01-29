/**
 * Created by SAMSUNG on 29.1.2017.
 */
var SequelizeAuto = require('sequelize-auto');
var auto = new new SequelizeAuto('TRDatabase', 'root', '4596ak69', {
    host: '178.62.223.153',
    port: 3306,
    dialect: 'mysql'
});

auto.run(function (err) {
    if (err) throw err;

    console.log(auto.tables); // table list
    console.log(auto.foreignKeys); // foreign key list
});