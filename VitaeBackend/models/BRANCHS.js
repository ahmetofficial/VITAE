/* jshint indent: 2 */

module.exports = function(sequelize, DataTypes) {
  return sequelize.define('BRANCHS', {
    branch_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      primaryKey: true,
      autoIncrement: true
    },
    branch_name: {
      type: DataTypes.STRING,
      allowNull: false
    },
    clinic_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      references: {
        model: 'CLINICS',
        key: 'clinic_id'
      }
    }
  }, {
    tableName: 'BRANCHS'
  });
};
