/* jshint indent: 2 */

module.exports = function(sequelize, DataTypes) {
  return sequelize.define('HOSPITALS_HAVE_DOCTORS', {
    hospital_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      references: {
        model: 'HOSPITALS',
        key: 'hospital_id'
      }
    },
    user_id: {
      type: DataTypes.STRING,
      allowNull: false,
      references: {
        model: 'DOCTORS',
        key: 'user_id'
      }
    },
    clinic_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      references: {
        model: 'CLINICS',
        key: 'clinic_id'
      }
    },
    hiring_date: {
      type: DataTypes.DATE,
      allowNull: false
    }
  }, {
    tableName: 'HOSPITALS_HAVE_DOCTORS'
  });
};
