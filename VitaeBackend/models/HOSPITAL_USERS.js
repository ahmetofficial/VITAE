/* jshint indent: 2 */

module.exports = function(sequelize, DataTypes) {
  return sequelize.define('HOSPITAL_USERS', {
    user_id: {
      type: DataTypes.STRING,
      allowNull: false,
      primaryKey: true,
      references: {
        model: 'USERS',
        key: 'user_id'
      }
    },
    hospital_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      references: {
        model: 'HOSPITALS',
        key: 'hospital_id'
      }
    }
  }, {
    tableName: 'HOSPITAL_USERS'
  });
};
