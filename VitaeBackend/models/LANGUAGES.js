/* jshint indent: 2 */

module.exports = function(sequelize, DataTypes) {
  return sequelize.define('LANGUAGES', {
    language_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      primaryKey: true
    },
    language_name: {
      type: DataTypes.STRING,
      allowNull: false
    }
  }, {
    tableName: 'LANGUAGES'
  });
};
