/* jshint indent: 2 */

module.exports = function(sequelize, DataTypes) {
  return sequelize.define('DOCTORS', {
    user_id: {
      type: DataTypes.STRING,
      allowNull: false,
      primaryKey: true,
      references: {
        model: 'USERS',
        key: 'user_id'
      }
    },
    user_last_name: {
      type: DataTypes.STRING,
      allowNull: false
    },
    gender: {
      type: DataTypes.STRING,
      allowNull: false
    },
    branch_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      references: {
        model: 'BRANCHS',
        key: 'branch_id'
      }
    },
    doctor_type_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      references: {
        model: 'DOCTOR_TYPES',
        key: 'doctor_type_id'
      }
    },
    birthday: {
      type: DataTypes.DATE,
      allowNull: false
    },
    blood_type_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      references: {
        model: 'BLOOD_TYPES',
        key: 'blood_type_id'
      }
    }
  }, {
    tableName: 'DOCTORS'
  });
};
