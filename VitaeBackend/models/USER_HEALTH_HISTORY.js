/* jshint indent: 2 */

module.exports = function(sequelize, DataTypes) {
  return sequelize.define('USER_HEALTH_HISTORY', {
    user_id: {
      type: DataTypes.STRING,
      allowNull: false,
      primaryKey: true,
      references: {
        model: 'USERS',
        key: 'user_id'
      }
    },
    disease_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      primaryKey: true,
      references: {
        model: 'DISEASES',
        key: 'disease_id'
      }
    },
    disease_diagnosis_date: {
      type: DataTypes.DATE,
      allowNull: false
    },
    disease_system_reg_date: {
      type: DataTypes.DATE,
      allowNull: false
    },
    disease_finish_date: {
      type: DataTypes.DATE,
      allowNull: true
    },
    disease_level_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      references: {
        model: 'DISEASE_LEVEL',
        key: 'disease_level_id'
      }
    },
    disease_state_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      references: {
        model: 'STATE',
        key: 'state_id'
      }
    }
  }, {
    tableName: 'USER_HEALTH_HISTORY'
  });
};
