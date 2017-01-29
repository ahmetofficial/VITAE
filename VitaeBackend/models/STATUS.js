/* jshint indent: 2 */

module.exports = function(sequelize, DataTypes) {
  return sequelize.define('STATUS', {
    status_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      primaryKey: true
    },
    status_description: {
      type: DataTypes.STRING,
      allowNull: false
    }
  }, {
    tableName: 'STATUS'
  });
};
