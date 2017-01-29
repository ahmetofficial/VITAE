/* jshint indent: 2 */

module.exports = function(sequelize, DataTypes) {
  return sequelize.define('GENERAL_TYPE_GROUPS', {
    general_type_id: {
      type: DataTypes.STRING,
      allowNull: false,
      primaryKey: true
    },
    general_type_name: {
      type: DataTypes.STRING,
      allowNull: false
    }
  }, {
    tableName: 'GENERAL_TYPE_GROUPS'
  });
};
