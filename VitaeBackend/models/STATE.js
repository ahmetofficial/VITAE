/* jshint indent: 2 */

module.exports = function(sequelize, DataTypes) {
  return sequelize.define('STATE', {
    state_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      primaryKey: true
    },
    state_name: {
      type: DataTypes.STRING,
      allowNull: false
    }
  }, {
    tableName: 'STATE'
  });
};
