/* jshint indent: 2 */

module.exports = function(sequelize, DataTypes) {
  return sequelize.define('USERS_HAVE_HOSPITAL_CLINICS', {
    user_id: {
      type: DataTypes.STRING,
      allowNull: false,
      references: {
        model: 'USERS',
        key: 'user_id'
      }
    },
    hospital_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      references: {
        model: 'HOSPITALS_HAVE_CLINICS',
        key: 'hospital_id'
      }
    },
    clinic_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      references: {
        model: 'HOSPITALS_HAVE_CLINICS',
        key: 'clinic_id'
      }
    }
  }, {
    tableName: 'USERS_HAVE_HOSPITAL_CLINICS'
  });
};
