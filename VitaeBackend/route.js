/**
 * Created by SAMSUNG on 29.1.2017.
 */
module.exports = function (modules, app) {
    modules.forEach(function (module) {
        app.use('/' + (module.path || module), require('./modules/' + (module.name || module) + '/router'));
    });
}