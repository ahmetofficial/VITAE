/* jshint indent: 2 */

module.exports = function(sequelize, DataTypes) {
  return sequelize.define('PROVINCES', {
    province_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      primaryKey: true
    },
    state_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      references: {
        model: 'STATES',
        key: 'state_id'
      }
    },
    province_name: {
      type: DataTypes.STRING,
      allowNull: false
    }
  }, {
    tableName: 'PROVINCES'
  });
};
