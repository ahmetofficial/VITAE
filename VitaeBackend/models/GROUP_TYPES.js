/* jshint indent: 2 */

module.exports = function(sequelize, DataTypes) {
  return sequelize.define('GROUP_TYPES', {
    type_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      primaryKey: true
    },
    type_name: {
      type: DataTypes.STRING,
      allowNull: false
    }
  }, {
    tableName: 'GROUP_TYPES'
  });
};
