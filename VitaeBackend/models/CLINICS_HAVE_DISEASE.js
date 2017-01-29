/* jshint indent: 2 */

module.exports = function(sequelize, DataTypes) {
  return sequelize.define('CLINICS_HAVE_DISEASE', {
    disease_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      references: {
        model: 'DISEASES',
        key: 'disease_id'
      }
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
    tableName: 'CLINICS_HAVE_DISEASE'
  });
};
