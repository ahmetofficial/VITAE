/* jshint indent: 2 */

module.exports = function(sequelize, DataTypes) {
  return sequelize.define('STATES', {
    state_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      primaryKey: true
    },
    state_name: {
      type: DataTypes.STRING,
      allowNull: false
    },
    state_code: {
      type: DataTypes.STRING,
      allowNull: false
    },
    country_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      references: {
        model: 'COUNTRIES',
        key: 'country_id'
      }
    }
  }, {
    tableName: 'STATES'
  });
};
