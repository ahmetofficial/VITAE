/* jshint indent: 2 */

module.exports = function(sequelize, DataTypes) {
  return sequelize.define('USERS_HOSPITAL_RATES', {
    user_id: {
      type: DataTypes.STRING,
      allowNull: false,
      references: {
        model: 'USERS',
        key: 'user_id'
      }
    },
    user_rate: {
      type: DataTypes.INTEGER(11),
      allowNull: false
    },
    hospital_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      references: {
        model: 'HOSPITALS',
        key: 'hospital_id'
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
    user_comment: {
      type: DataTypes.STRING,
      allowNull: true
    }
  }, {
    tableName: 'USERS_HOSPITAL_RATES'
  });
};
