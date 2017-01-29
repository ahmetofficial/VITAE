/* jshint indent: 2 */

module.exports = function(sequelize, DataTypes) {
  return sequelize.define('TREATMENT_STATE_HISTORY', {
    user_id: {
      type: DataTypes.STRING,
      allowNull: false,
      references: {
        model: 'USER_TREATMENT_HISTORY',
        key: 'user_id'
      }
    },
    disease_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      references: {
        model: 'USER_TREATMENT_HISTORY',
        key: 'disease_id'
      }
    },
    treatment_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      references: {
        model: 'USER_TREATMENT_HISTORY',
        key: 'treatment_id'
      }
    },
    treatment_state_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      references: {
        model: 'STATE',
        key: 'state_id'
      }
    },
    treatment_state_start_date: {
      type: DataTypes.DATE,
      allowNull: false
    },
    treatment_state_finish_date: {
      type: DataTypes.DATE,
      allowNull: true
    }
  }, {
    tableName: 'TREATMENT_STATE_HISTORY'
  });
};
