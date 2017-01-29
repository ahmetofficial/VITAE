/**
 * Created by SAMSUNG on 29.1.2017.
 */

var models = require('../models/index');

router.post('/users', function(req, res) {
    models.prescript.create({
        email: req.body.email
    }).then(function(user) {
        res.json(user);
    });
});

route.post('/users', function(req, res) {
    models.User.create({
        email: req.body.email
    }).then(function(user) {
        res.json(user);
    });
});