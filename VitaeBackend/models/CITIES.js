/* jshint indent: 2 */

module.exports = function(sequelize, DataTypes) {
  return sequelize.define('CITIES', {
    city_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      primaryKey: true
    },
    city_name: {
      type: DataTypes.STRING,
      allowNull: false
    },
    province_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      references: {
        model: 'PROVINCES',
        key: 'province_id'
      }
    }
  }, {
    tableName: 'CITIES'
  });
};
