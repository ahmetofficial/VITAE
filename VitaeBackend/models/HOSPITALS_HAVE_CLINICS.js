/* jshint indent: 2 */

module.exports = function(sequelize, DataTypes) {
  return sequelize.define('HOSPITALS_HAVE_CLINICS', {
    hospital_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      primaryKey: true,
      references: {
        model: 'HOSPITALS',
        key: 'hospital_id'
      }
    },
    clinic_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      primaryKey: true,
      references: {
        model: 'CLINICS',
        key: 'clinic_id'
      }
    },
    total_vote: {
      type: DataTypes.INTEGER(11),
      allowNull: false
    },
    vote_count: {
      type: DataTypes.BIGINT,
      allowNull: false
    },
    overall_score: {
      type: "DOUBLE(10,5)",
      allowNull: false
    },
    comment: {
      type: DataTypes.STRING,
      allowNull: false
    }
  }, {
    tableName: 'HOSPITALS_HAVE_CLINICS'
  });
};
